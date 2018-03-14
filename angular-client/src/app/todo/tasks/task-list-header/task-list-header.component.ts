import {Component, Output, EventEmitter, Input} from '@angular/core';
import {Task} from "../task";
import {Todo} from "../../todo";

@Component({
  selector: 'app-task-list-header',
  templateUrl: './task-list-header.component.html',
  styleUrls: ['./task-list-header.component.css']
})
export class TaskListHeaderComponent {

  newTask: Task = new Task();

  @Input()
  todo: Todo;

  @Output()
  add: EventEmitter<Task> = new EventEmitter();

  constructor() {
  }

  addTask() {
    this.add.emit(this.newTask);
    this.newTask = new Task();
  }


}
