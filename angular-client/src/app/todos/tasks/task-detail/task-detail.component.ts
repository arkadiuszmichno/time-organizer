import {Component, OnInit} from '@angular/core';
import {Task} from '../task.model';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {TaskService} from '../task.service';

@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.css']
})
export class TaskDetailComponent implements OnInit {
  task: Task;
  taskId: number;
  todoId: number;

  constructor(private taskService: TaskService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe(
      (params: Params) => {
        this.taskId = +params['id'];
        this.task = this.taskService.getTask(this.taskId);

      }
    );
  }

  onEditTask() {
    this.router.navigate(['edit'], {relativeTo: this.route});
  }

  onDeleteTask() {
    this.taskService.deleteTask(this.taskId);
    this.todoId = this.taskService.getTodoId();
    this.router.navigate(['todos/' + this.todoId + '/tasks']);
  }

}
