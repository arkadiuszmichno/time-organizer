import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {of} from "rxjs/observable/of";
import {Observable} from "rxjs";
import {Todo} from "./todo";
import {catchError} from "rxjs/operators";

const API_URL = environment.apiUrl;

@Injectable()
export class TodoDataService {

  constructor(private http: HttpClient) {
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error, 'Operation: ${operation}');

      return of(result as T);
    }
  }

  getAllTodos(): Observable<Todo[]> {
    return this.http.get<Todo[]>(API_URL + /list/)
      .pipe(
        catchError(this.handleError('getTodos', []))
      )
  }

}
