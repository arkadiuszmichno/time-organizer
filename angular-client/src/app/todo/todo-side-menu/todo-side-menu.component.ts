import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-todo-side-menu',
  templateUrl: './todo-side-menu.component.html',
  styleUrls: ['./todo-side-menu.component.css']
})
export class TodoSideMenuComponent implements OnInit {

  constructor(route: ActivatedRoute) {
    route.params.subscribe(params => console.log("side menu id parameter",params['id']));
  }


  ngOnInit() {
  }

}
