import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContentAppComponent } from './content-app.component';

describe('ContentAppComponent', () => {
  let component: ContentAppComponent;
  let fixture: ComponentFixture<ContentAppComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ContentAppComponent]
    });
    fixture = TestBed.createComponent(ContentAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
