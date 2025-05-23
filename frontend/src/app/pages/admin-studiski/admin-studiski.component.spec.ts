import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminStudiskiComponent } from './admin-studiski.component';

describe('AdminStudiskiComponent', () => {
  let component: AdminStudiskiComponent;
  let fixture: ComponentFixture<AdminStudiskiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminStudiskiComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminStudiskiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
