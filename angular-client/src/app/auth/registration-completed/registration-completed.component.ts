import {Component, OnInit} from '@angular/core';
import {AuthService} from '../auth.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-registration-completed',
  templateUrl: './registration-completed.component.html',
  styleUrls: ['./registration-completed.component.css']
})
export class RegistrationCompletedComponent implements OnInit {
  verificationToken = '';

  constructor(private authService: AuthService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe(
      params => {
        this.verificationToken = params['token'];
        if (this.verificationToken !== null && this.verificationToken !== '') {
          this.authService.sendVerificationToken(this.verificationToken);
        }
      }
    );
  }

}
