import {Task} from './tasks/task.model';

export class Todo {
  public id: number;
  public name: string;
  public tasks: Task[];


  constructor(name: string) {
    this.name = name;
  }
}
