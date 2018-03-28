import {Task} from './task.model';
import {Subject} from 'rxjs/Subject';
import {Injectable} from '@angular/core';
import {DataSourceService} from '../../shared/data-source.service';

@Injectable()
export class TaskService {
  tasksUpdated = new Subject<Task[]>();
  tasks: Task[] = [];
  todoId = 19;

  constructor(private dataSourceService: DataSourceService) {
    this.getTasksFromServer();
  }

  setTodoList(todoId: number) {
    this.todoId = todoId;
    this.getTasksFromServer();
    this.tasksUpdated.next(this.tasks.slice());
  }

  getTasksFromServer() {
    this.dataSourceService.getTasks(this.todoId).subscribe(
      (newTasks) => {
        this.tasks = newTasks;
        this.tasksUpdated.next(this.tasks.slice());
      }
    );
  }

  getTodoId() {
    return this.todoId;
  }

  getTasks() {
    return this.tasks.slice();

  }

  getTask(id: number) {
    return this.tasks.find(task => task.id === id);
  }

  addTask(task: Task) {
    console.log(task.endDate);
    this.dataSourceService.addTask(task, this.todoId).subscribe(
      (resource) => {
        console.log(resource);
        task.id = +resource.id;
      }
    );
    this.tasks.push(task);
    this.tasksUpdated.next(this.tasks.slice());
  }

  updateTask(index: number, task: Task) {
    this.dataSourceService.updateTask(index, task).subscribe(
      (response) => console.log(response)
    );
    const updateItem = this.tasks.find(this.findIndexToUpdate, index);
    const i = this.tasks.indexOf(updateItem);

    this.tasks[i] = task;
    this.tasks[i].id = index;

    this.tasksUpdated.next(this.tasks.slice());
  }

  findIndexToUpdate(newItem) {
    return newItem.id === this;
  }

  deleteTask(index: number) {
    this.dataSourceService.deleteTask(index).subscribe(
      (response) => console.log(response)
    );
    this.tasks = this.tasks.filter(task => task.id !== index);

    this.tasksUpdated.next(this.tasks.slice());
  }
}

