import {Routes} from "@angular/router";
import {HomeComponent} from "./app/home/home.component";
import {TodoComponent} from "./app/todo/todo.component";
import {TodoSideMenuComponent} from "./app/todo/todo-side-menu/todo-side-menu.component";

export const routerConfig: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'todo',
    component: TodoComponent,
    children: [
      {
        path: '',
        outlet: 'sidemenu',
        component: TodoSideMenuComponent
      }
    ]
  },
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: '/home',
    pathMatch: 'full'
  }
];
