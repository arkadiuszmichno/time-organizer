import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../auth/auth.service';
import {Subscription} from 'rxjs/Subscription';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {
  isAuthenticated = false;
  private subscription: Subscription;

  constructor(private router: Router, private authService: AuthService) {
  }

  ngOnInit() {
    this.subscription = this.authService.authenticatedUpdated.subscribe(
      (auth) => this.isAuthenticated = auth
    );
  }

  onLogout() {
    this.isAuthenticated = false;
    this.authService.logout();
    this.router.navigate(['/']);
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }


}
