import {Component, OnInit} from '@angular/core';
import {TodoService} from '../todo.service';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Todo} from '../todo.model';

@Component({
  selector: 'app-todo-list-edit',
  templateUrl: './todo-list-edit.component.html',
  styleUrls: ['./todo-list-edit.component.css']
})
export class TodoListEditComponent implements OnInit {
  id: number;
  todoForm: FormGroup;

  constructor(private todoService: TodoService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe(
      (params: Params) => {
        this.id = +params['listId'];
        this.initForm();
      }
    );
  }

  private initForm() {
    const todo = this.todoService.getTodo(this.id);
    const todoName = todo.name;
    this.todoForm = new FormGroup({
      'name': new FormControl(todoName, [Validators.required, Validators.minLength(3)])
    });
  }

  onSubmit() {
    const newTodo = new Todo(this.todoForm.value['name']);
    this.todoService.updateTodo(this.id, newTodo);
    this.onCancel();
  }

  onCancel() {
    this.router.navigate(['../tasks'], {relativeTo: this.route});
  }
}
