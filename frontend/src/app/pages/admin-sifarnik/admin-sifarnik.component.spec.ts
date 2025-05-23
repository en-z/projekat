import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSifarnikComponent } from './admin-sifarnik.component';

describe('AdminSifarnikComponent', () => {
  let component: AdminSifarnikComponent;
  let fixture: ComponentFixture<AdminSifarnikComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminSifarnikComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminSifarnikComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
