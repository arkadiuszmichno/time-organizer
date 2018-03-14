import { Task } from './task';

describe('Task', () => {
  it('should create an instance', () => {
    expect(new Task()).toBeTruthy();
  });

  it('should accept values in the constructor', () => {
    let task = new Task({
      title: 'hello',
      complete: true
    });
    expect(task.name).toEqual('hello');
    expect(task.complete).toEqual(true);
  })
});
