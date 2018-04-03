import {Injectable} from '@angular/core';
import {DataSourceService} from '../shared/data-source.service';
import {Todo} from './todo.model';
import {Subject} from 'rxjs/Subject';

@Injectable()
export class TodoService {
  todos: Todo[] = [];
  todosUpdated = new Subject<Todo[]>();

  constructor(private dataSourceService: DataSourceService) {
    this.dataSourceService.getTodos().subscribe(
      (newTodos) => {
        this.todos = newTodos;
        this.todosUpdated.next(this.todos.slice());
      }
    );
  }

  getTodos() {
    return this.todos.slice();
  }

  addTodo(todo: Todo) {
    this.dataSourceService.addTodo(todo).subscribe(
      (response) => {
        this.todos.push(response);
        this.todosUpdated.next(this.todos.slice());
      });
  }

  getTodo(todoId: number) {
    return this.todos.find(todo => todo.id === todoId);
  }

  deleteTodo(todoId: number) {
    this.dataSourceService.deleteTodo(todoId).subscribe(
      (response) => {
        console.log(response);
      }
    );
    this.todos = this.todos.filter(todo => todo.id !== todoId);
    this.todosUpdated.next(this.todos.slice());
  }

  updateTodo(id: number, newTodo: Todo) {
    this.dataSourceService.updateTodo(id, newTodo).subscribe(
      (response) => console.log(response)
    );

    const updateItem = this.todos.find(this.findIndexToUpdate, id);
    const i = this.todos.indexOf(updateItem);
    this.todos[i].name = newTodo.name;
    this.todosUpdated.next(this.todos.slice());
  }

  findIndexToUpdate(newItem) {
    return newItem.id === this;
  }
}
