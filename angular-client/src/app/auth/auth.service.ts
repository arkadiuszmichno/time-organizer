import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TokenStorage} from '../shared/token.storage';
import {Subject} from 'rxjs/Subject';

@Injectable()
export class AuthService {
  authenticatedUpdated = new Subject<any>();
  baseURL = 'http://localhost:8080/auth';

  constructor(private httpClient: HttpClient, private token: TokenStorage) {
  }


  signupUser(username: string, name: string, email: string, password: string) {
    return this.httpClient.post(this.baseURL + '/signup', {username, name, email, password});
  }

  signIn(usernameOrEmail: string, password: string) {
    return this.httpClient.post(this.baseURL + '/signin', {usernameOrEmail, password});
  }

  logout() {
    this.token.signOut();
  }

  isAuthenticated() {
    const auth = this.token.getToken() !== null;
    this.authenticatedUpdated.next(auth);
    return auth;
  }
}
