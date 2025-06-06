import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDodajKnjiguComponent } from './admin-dodaj-knjigu.component';

describe('AdminDodajKnjiguComponent', () => {
  let component: AdminDodajKnjiguComponent;
  let fixture: ComponentFixture<AdminDodajKnjiguComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminDodajKnjiguComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminDodajKnjiguComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
