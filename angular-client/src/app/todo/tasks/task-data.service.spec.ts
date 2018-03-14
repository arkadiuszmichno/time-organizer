import {TestBed, inject} from '@angular/core/testing';

import {TaskDataService} from './task-data.service';
import {Task} from "./task";

describe('TaskDataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TaskDataService]
    });
  });

  it('should be created', inject([TaskDataService], (service: TaskDataService) => {
    expect(service).toBeTruthy();
  }));

  describe('#getAllTasks()', () => {
    it('should return an empty array by default', inject([TaskDataService], (service: TaskDataService) => {
      expect(service.getAllTasks()).toEqual([]);
    }));

    it('should return all tasks', inject([TaskDataService], (service: TaskDataService) => {
      let task1 = new Task({title: 'Hello 1', complete: false});
      let task2 = new Task({title: 'Hello 2', complete: true});
      service.addTask(task1);
      service.addTask(task2);
      expect(service.getAllTasks()).toEqual([task1, task2]);
    }));
  });

  describe('#save(todo)', () => {
    it('should automatically assign an incrementing id', inject([TaskDataService], (service: TaskDataService) => {
      let task1 = new Task({title: 'Hello 1', complete: false});
      let task2 = new Task({title: 'Hello 2', complete: true});
      service.addTask(task1);
      service.addTask(task2);
      expect(service.getTaskById(task1.id)).toEqual(task1);
      expect(service.getTaskById(task2.id)).toEqual(task2);
    }));

  });

  describe('#deleteTaskById(id)', () => {

    it('should remove task with the corresponding id', inject([TaskDataService], (service: TaskDataService) => {
      let task1 = new Task({title: 'Hello 1', complete: false});
      let task2 = new Task({title: 'Hello 2', complete: true});
      service.addTask(task1);
      service.addTask(task2);
      expect(service.getAllTasks()).toEqual([task1, task2]);
      service.deleteTaskById(1);
      expect(service.getAllTasks()).toEqual([task2]);
      service.deleteTaskById(2);
      expect(service.getAllTasks()).toEqual([]);
    }));

    it('should not removing anything if task with the corresponding id is not found', inject([TaskDataService], (service: TaskDataService) => {
      let task1 = new Task({title: 'Hello 1', complete: false});
      let task2 = new Task({title: 'Hello 2', complete: true});
      service.addTask(task1);
      service.addTask(task2);
      expect(service.getAllTasks()).toEqual([task1, task2]);
      service.deleteTaskById(3);
      expect(service.getAllTasks()).toEqual([task1, task2]);
    }));

  });

  describe('#updateTaskById(id, values)', () => {

    it('should return task with the corresponding id and updated data', inject([TaskDataService], (service: TaskDataService) => {
      let task1 = new Task({title: 'Hello 1', complete: false});
      service.addTask(task1);
      let updatedTask = service.updateTaskById(1, {
        title: 'new name'
      });
      expect(updatedTask.name).toEqual('new name');
    }));

    it('should return null if task is not found', inject([TaskDataService], (service: TaskDataService) => {
      let task1 = new Task({title: 'Hello 1', complete: false});
      service.addTask(task1);
      let updatedTask = service.updateTaskById(2, {
        title: 'new name'
      });
      expect(updatedTask).toEqual(null);
    }));

  });

  describe('#toggleTaskComplete(task)', () => {
    it('should return the updated task with inverse complete status', inject([TaskDataService], (service: TaskDataService) => {
      let task1 = new Task({title: 'Hello 1', complete: false});
      service.addTask(task1);
      let updatedTask = service.toggleTaskComplete(task1);
      expect(updatedTask.complete).toEqual(true);
      service.toggleTaskComplete(task1);
      expect((updatedTask.complete)).toEqual(false);
    }));

  });

});
