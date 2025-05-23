import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDodajStudiskiComponent } from './admin-dodaj-studiski.component';

describe('AdminDodajStudiskiComponent', () => {
  let component: AdminDodajStudiskiComponent;
  let fixture: ComponentFixture<AdminDodajStudiskiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminDodajStudiskiComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminDodajStudiskiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
