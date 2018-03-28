import {Component, Input, OnInit} from '@angular/core';
import {Todo} from '../../todo.model';

@Component({
  selector: 'app-todo-item',
  templateUrl: './todo-item.component.html',
  styleUrls: ['./todo-item.component.css']
})
export class TodoItemComponent implements OnInit {
  @Input() itemTodo: Todo;
  @Input() todoId: number;

  constructor() {
  }

  ngOnInit() {
  }

}
