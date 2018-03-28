import {Component, OnInit} from '@angular/core';
import {Task} from './task.model';
import {TaskService} from './task.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css'],

})
export class TasksComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {

  }

}
