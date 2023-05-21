import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OnePostComponent } from './one-post.component';

describe('OnePostComponent', () => {
  let component: OnePostComponent;
  let fixture: ComponentFixture<OnePostComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OnePostComponent]
    });
    fixture = TestBed.createComponent(OnePostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
