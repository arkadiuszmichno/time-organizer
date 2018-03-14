import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoSideMenuComponent } from './todo-side-menu.component';

describe('TodoSideMenuComponent', () => {
  let component: TodoSideMenuComponent;
  let fixture: ComponentFixture<TodoSideMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TodoSideMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TodoSideMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
