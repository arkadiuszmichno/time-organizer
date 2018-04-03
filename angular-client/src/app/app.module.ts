import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {HeaderComponent} from './header/header.component';


import {DropdownDirective} from './shared/dropdown.directive';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {PomodoroComponent} from './pomodoro/pomodoro.component';
import {AppRoutingModule} from './app-routing.module';

import {SignupComponent} from './auth/signup/signup.component';
import {SigninComponent} from './auth/signin/signin.component';
import {AuthService} from './auth/auth.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthInterceptor} from './shared/auth.interceptor';
import {TokenStorage} from './shared/token.storage';
import {HomeComponent} from './home/home.component';
import {AuthGuardService} from './auth/auth-guard.service';
import {DataSourceService} from './shared/data-source.service';
import {TodosComponent} from './todos/todos.component';
import {TodoListComponent} from './todos/todo-list/todo-list.component';
import {TasksComponent} from './todos/tasks/tasks.component';
import {TaskListComponent} from './todos/tasks/task-list/task-list.component';
import {TaskDetailComponent} from './todos/tasks/task-detail/task-detail.component';
import {TaskItemComponent} from './todos/tasks/task-list/task-item/task-item.component';
import {TaskAddComponent} from './todos/tasks/task-list/task-add/task-add.component';
import {TaskStartComponent} from './todos/tasks/task-start/task-start.component';
import {TaskEditComponent} from './todos/tasks/task-edit/task-edit.component';
import {TaskService} from './todos/tasks/task.service';
import {TodoService} from './todos/todo.service';
import {TodoItemComponent} from './todos/todo-list/todo-item/todo-item.component';
import {MatCardModule} from '@angular/material/card';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {MatDatepickerModule, MatDividerModule, MatFormFieldModule, MatNativeDateModule} from '@angular/material';
import {DlDateTimePickerDateModule} from 'angular-bootstrap-datetimepicker';
import {CommonModule} from '@angular/common';
import {ConfirmComponent} from './auth/confirm/confirm.component';
import {RegistrationCompletedComponent} from './auth/registration-completed/registration-completed.component';
import { TodoListEditComponent } from './todos/todo-list-edit/todo-list-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    TasksComponent,
    TaskListComponent,
    TaskItemComponent,
    TaskDetailComponent,
    TaskAddComponent,
    DropdownDirective,
    PomodoroComponent,
    TaskStartComponent,
    TaskEditComponent,
    SignupComponent,
    SigninComponent,
    HomeComponent,
    TodosComponent,
    TodoListComponent,
    TodoItemComponent,
    ConfirmComponent,
    RegistrationCompletedComponent,
    TodoListEditComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    MatCardModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatDividerModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    DlDateTimePickerDateModule,
    CommonModule
  ],
  providers: [
    TaskService,
    AuthService,
    TokenStorage,
    AuthGuardService,
    DataSourceService,
    TodoService,
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
