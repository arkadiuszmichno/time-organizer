import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {PomodoroComponent} from './pomodoro/pomodoro.component';

import {SignupComponent} from './auth/signup/signup.component';
import {SigninComponent} from './auth/signin/signin.component';
import {HomeComponent} from './home/home.component';
import {AuthGuardService} from './auth/auth-guard.service';
import {TodosComponent} from './todos/todos.component';
import {TasksComponent} from './todos/tasks/tasks.component';
import {TaskStartComponent} from './todos/tasks/task-start/task-start.component';
import {TaskEditComponent} from './todos/tasks/task-edit/task-edit.component';
import {TaskDetailComponent} from './todos/tasks/task-detail/task-detail.component';

const appRoutes: Routes = [
  {path: '', component: HomeComponent, pathMatch: 'full'},
  {
    path: 'todos', component: TodosComponent, children: [
      {
        path: ':listId/tasks', component: TasksComponent, children: [
          {path: '', component: TaskStartComponent},
          {path: 'new', component: TaskEditComponent},
          {path: ':id', component: TaskDetailComponent},
          {path: ':id/edit', component: TaskEditComponent}
        ], canActivate: [AuthGuardService]
      },
    ],
    canActivate: [AuthGuardService]
  },
  {path: 'pomodoro', component: PomodoroComponent, canActivate: [AuthGuardService]},
  {path: 'signup', component: SignupComponent},
  {path: 'signin', component: SigninComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
