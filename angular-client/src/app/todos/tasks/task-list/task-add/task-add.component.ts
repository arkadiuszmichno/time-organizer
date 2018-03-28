import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Task} from '../../task.model';
import {TaskService} from '../../task.service';
import {ActivatedRoute, Router} from '@angular/router';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-task-add',
  templateUrl: './task-add.component.html',
  styleUrls: ['./task-add.component.css']
})
export class TaskAddComponent implements OnInit {

  constructor(private taskService: TaskService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit() {
  }

  onAddItem(form: NgForm) {
    const taskName = form.value['name'];
    const newTask = new Task(taskName);
    this.taskService.addTask(newTask);
  }

  onAdvancedAdd() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }
}
