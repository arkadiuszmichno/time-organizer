import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {TaskService} from '../task.service';
import {Task} from '../task.model';

@Component({
  selector: 'app-task-edit',
  templateUrl: './task-edit.component.html',
  styleUrls: ['./task-edit.component.css']
})
export class TaskEditComponent implements OnInit {
  id: number;
  editMode: boolean;
  taskForm: FormGroup;
  minDate = new Date();


  constructor(private route: ActivatedRoute, private taskService: TaskService,
              private router: Router) {
  }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.id = +params['id'];
          this.editMode = params['id'] != null;
          this.initForm();
        }
      );
  }

  onSubmit() {
    const newTask = new Task(this.taskForm.value['name'], this.taskForm.value['description'], this.taskForm.value['date']);
    if (this.editMode) {
      this.taskService.updateTask(this.id, newTask);
    } else {
      this.taskService.addTask(newTask);
    }
    this.onCancel();
  }

  onCancel() {
    this.router.navigate(['../'], {relativeTo: this.route});
  }

  private initForm() {
    let taskName = '';
    let taskDescription = '';
    let taskDate = '';

    if (this.editMode) {
      const task = this.taskService.getTask(this.id);
      taskName = task.name;
      if (task.description) {
        taskDescription = task.description;
      }
      taskDate = task.endDate;
    }
    this.taskForm = new FormGroup({
      'name': new FormControl(taskName, [Validators.required, Validators.minLength(3)]),
      'description': new FormControl(taskDescription),
      'date': new FormControl(taskDate)
    });
  }

}
