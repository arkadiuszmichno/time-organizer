import {Component, OnInit} from "@angular/core";
import {TaskDataService} from "./tasks/task-data.service";
import {Task} from "./tasks/task";
import {Todo} from "./todo";

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css'],
  providers: [TaskDataService]
})
export class TodoComponent implements OnInit {

  tasks: Task[] = [];

  todo: Todo;

  constructor(private taskDataService: TaskDataService) {
  }

  public ngOnInit() {
    this.taskDataService
      .getAllTasks()
      .subscribe(tasks => this.tasks = tasks)
  }
  getTasks(): void {
    this.taskDataService
      .getAllTasks()
      .subscribe(tasks => this.tasks = tasks)
  }
  setList(todo: Todo) {
    this.todo = todo;
    this.taskDataService.todoId = todo.id;
    this.getTasks();
  }

  onAddTask(task: Task) {
    this.taskDataService
      .addTask(task)
      .subscribe(
        (data: Task) => {this.tasks.push(data)}
      );
  }

  onToggleTaskComplete(task) {
    this.taskDataService.toggleTaskComplete(task).subscribe((updatedTask) => {
      task = updatedTask;
    });
  }

  onRemoveTask(task) {
    this.taskDataService.deleteTask(task).subscribe();
    this.tasks = this.tasks.filter(t => t.id !== task.id);
  }

}
