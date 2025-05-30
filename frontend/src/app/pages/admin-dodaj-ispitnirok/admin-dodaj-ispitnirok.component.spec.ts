import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDodajIspitnirokComponent } from './admin-dodaj-ispitnirok.component';

describe('AdminDodajIspitnirokComponent', () => {
  let component: AdminDodajIspitnirokComponent;
  let fixture: ComponentFixture<AdminDodajIspitnirokComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminDodajIspitnirokComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminDodajIspitnirokComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
