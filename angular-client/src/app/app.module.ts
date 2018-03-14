import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { TaskListHeaderComponent } from './todo/tasks/task-list-header/task-list-header.component';
import { TaskListComponent } from './todo/tasks/task-list/task-list.component';
import { TaskListItemComponent } from './todo/tasks/task-list-item/task-list-item.component';
import { TaskListFooterComponent } from './todo/tasks/task-list-footer/task-list-footer.component';
import {TaskDataService} from "./todo/tasks/task-data.service";
import { HttpClientModule } from '@angular/common/http';
import {routerConfig} from "../router.config";
import {RouterModule} from "@angular/router";
import { HomeComponent } from './home/home.component';
import { TodoComponent } from './todo/todo.component';
import { TodoSideMenuComponent } from './todo/todo-side-menu/todo-side-menu.component';
import { TodoListComponent } from './todo/todo-list/todo-list.component';
import { TodoDataService } from './todo/todo-data.service';


@NgModule({
  declarations: [
    AppComponent,
    TaskListHeaderComponent,
    TaskListComponent,
    TaskListItemComponent,
    TaskListFooterComponent,
    HomeComponent,
    TodoComponent,
    TodoSideMenuComponent,
    TodoListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routerConfig)
  ],
  providers: [TaskDataService, TodoDataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
