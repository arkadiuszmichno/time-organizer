import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {AuthService} from '../auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  usernameInUse = false;
  emailInUse = false;

  constructor(private authService: AuthService) {
  }

  ngOnInit() {
  }

  onSignup(form: NgForm) {
    const username = form.value['username'];
    const name = form.value['name'];
    const email = form.value['email'];
    const password = form.value['password'];
    this.authService.signupUser(username, name, email, password).subscribe(
      (response) => {
        console.log(response)
      },
      (error) => {
        if (error.error.message === 'Username is already in use!') {
          this.usernameInUse = true;
        } else {
          this.usernameInUse = false;
        }
        if (error.error.message === 'Email is already in use!') {
          this.emailInUse = true;
        } else {
          this.emailInUse = false;
        }
        console.log(error);
      }
    );
  }
}
