import {Injectable} from "@angular/core";
import {Task} from "./task";
import {Observable} from "rxjs/Observable";
import {catchError} from "rxjs/operators";
import {of} from "rxjs/observable/of";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Todo} from "../todo";

const API_URL = environment.apiUrl;

@Injectable()
export class TaskDataService {

  todoId: number=1;

  constructor(private http: HttpClient) {
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error, 'Operation: ${operation}');

      return of(result as T);
    }
  }
  addTask(task: Task): Observable<Task> {
    return this.http.post<Task>(API_URL + '/list/1/task/', task)
      .pipe(
        catchError(this.handleError<Task>('addTask'))
      )
  }

  deleteTask(task: Task): Observable<Task> {
    return this.http.delete<Task>(API_URL + '/task/' + task.id).pipe(
      catchError(this.handleError<Task>('deleteTask'))
    )
  }

  updateTask(task: Task): Observable<any> {
    const url = '${this.API_URL}/task/${task.id}';

    return this.http.put(url, task).pipe(
      catchError(this.handleError<any>('update Product'))
    )
  }

  getAllTasks(): Observable<Task[]> {
    const url = `${API_URL}/list/${this.todoId}/task`;

    return this.http.get<Task[]>(url)
      .pipe(
        catchError(this.handleError('getTasks', []))
      );
  }

  getTaskById(id: number): Observable<Task> {
    const url = '${this.API_URL}/task/${id}';
    return this.http.get<Task>(url).pipe(
      catchError(this.handleError<Task>('getTask id=${id}'))
    )
  }

  toggleTaskComplete(task: Task) {
    task.complete = !task.complete;
    return this.updateTask(task);
  }

}
