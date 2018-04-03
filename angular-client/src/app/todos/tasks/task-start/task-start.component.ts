import {Component, OnInit} from '@angular/core';
import {Todo} from '../../todo.model';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {TodoService} from '../../todo.service';

@Component({
  selector: 'app-task-start',
  templateUrl: './task-start.component.html',
  styleUrls: ['./task-start.component.css']
})
export class TaskStartComponent implements OnInit {
  todo: Todo;
  todoId: number;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private todoService: TodoService) {
  }

  ngOnInit() {
    this.route.params.subscribe(
      (params: Params) => {
        this.todoId = +params['listId'];
        this.todo = this.todoService.getTodo(this.todoId);
      }
    );
  }

  onEditList() {
    this.router.navigate(['todos/' + this.todoId + '/edit']);

  }

  onDeleteList() {
    this.todoService.deleteTodo(this.todoId);
    this.router.navigate(['todos']);
  }
}
