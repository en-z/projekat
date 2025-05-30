import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDodajPredmetComponent } from './admin-dodaj-predmet.component';

describe('AdminDodajPredmetComponent', () => {
  let component: AdminDodajPredmetComponent;
  let fixture: ComponentFixture<AdminDodajPredmetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminDodajPredmetComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminDodajPredmetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
