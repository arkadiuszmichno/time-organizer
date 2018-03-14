export class Task {
  id: string;
  name: string = '';
  complete: boolean = false;

  constructor(values: Object = {}) {
    Object.assign(this,values);
  }
}
