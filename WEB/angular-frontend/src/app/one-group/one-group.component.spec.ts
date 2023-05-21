import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OneGroupComponent } from './one-group.component';

describe('OneGroupComponent', () => {
  let component: OneGroupComponent;
  let fixture: ComponentFixture<OneGroupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OneGroupComponent]
    });
    fixture = TestBed.createComponent(OneGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
