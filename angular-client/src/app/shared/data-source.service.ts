import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Task} from '../todos/tasks/task.model';
import {Todo} from '../todos/todo.model';


const BASE_URL = 'http://localhost:8080';

@Injectable()
export class DataSourceService {

  constructor(private httpClient: HttpClient) {
  }

  getTasks(todoId: number) {
    return this.httpClient.get<Task[]>(BASE_URL + '/lists/' + todoId + '/tasks');
  }

  getTask(id: number) {
    return this.httpClient.get<Task>(BASE_URL + '/tasks/' + id);
  }

  addTask(task: Task, todoId: number) {
    return this.httpClient.post<Task>(BASE_URL + '/lists/' + todoId + '/tasks', task);
  }

  updateTask(index: number, task: Task) {
    return this.httpClient.put<Task>(BASE_URL + '/tasks/' + index, task);
  }

  deleteTask(index: number) {
    return this.httpClient.delete<Task>(BASE_URL + '/tasks/' + index);
  }

  getTodos() {
    return this.httpClient.get<Todo[]>(BASE_URL + '/lists');
  }

  addTodo(todo: Todo) {
    return this.httpClient.post<Todo>(BASE_URL + '/lists', todo);
  }

  deleteTodo(todoId: number) {
    return this.httpClient.delete<Task>(BASE_URL + '/lists/' + todoId);
  }

  updateTodo(index: number, todo: Todo) {
    return this.httpClient.put<Todo>(BASE_URL + '/lists/' + index, todo);
  }
}
