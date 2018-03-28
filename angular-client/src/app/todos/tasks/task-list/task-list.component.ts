import {Component, OnDestroy, OnInit} from '@angular/core';
import {Task} from '../task.model';
import {TaskService} from '../task.service';
import {Subscription} from 'rxjs/Subscription';
import {ActivatedRoute, Params} from '@angular/router';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit, OnDestroy {
  tasks: Task[] = [];
  todoId: number;

  private subscritpion: Subscription;

  constructor(private taskService: TaskService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.params.subscribe(
      (params: Params) => {
          this.todoId = +params['listId'];
          this.taskService.setTodoList(this.todoId);
      }
    );
    this.tasks = this.taskService.getTasks();
    this.subscritpion = this.taskService.tasksUpdated.subscribe(
      (tasks: Task[]) => {
        this.tasks = tasks;
      }
    );
  }
  ngOnDestroy(): void {
    this.subscritpion.unsubscribe();
  }

}
