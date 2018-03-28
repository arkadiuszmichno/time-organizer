import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';
import {TokenStorage} from '../../shared/token.storage';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {
  errorOccured = false;

  constructor(private authService: AuthService,
              private router: Router,
              private token: TokenStorage) {
  }

  ngOnInit() {
  }

  onSignin(form: NgForm) {
    const usernameOrEmail = form.value['usernameOrEmail'];
    const password = form.value['password'];
    this.authService.signIn(usernameOrEmail, password).subscribe(
      (response) => {
        this.errorOccured = false;
        console.log(response['accessToken']);
        if (response['accessToken'] !== null) {
          this.token.saveToken(response['accessToken']);
        }

        this.router.navigate(['/todos']);
      },
      (error) => {
        if (error.error.status === 401) {
          this.errorOccured = true;
          form.reset();
        }
        console.log(error);
      }
    );
  }

}
