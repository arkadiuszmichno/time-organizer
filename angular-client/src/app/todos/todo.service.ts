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

}
