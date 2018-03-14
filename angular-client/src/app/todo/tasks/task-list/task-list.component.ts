import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Task} from '../task';
@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent {

  @Input()
  tasks: Task[];

  @Output()
  toggleComplete: EventEmitter<Task> = new EventEmitter();

  @Output()
  remove: EventEmitter<Task> = new EventEmitter();

  constructor() {
  }

  onToggleTaskComplete(task: Task) {
    this.toggleComplete.emit(task);
  }

  onRemoveTask(task: Task) {
    this.remove.emit(task);
  }

}
