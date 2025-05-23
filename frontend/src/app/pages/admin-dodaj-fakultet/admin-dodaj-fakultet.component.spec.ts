import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDodajFakultetComponent } from './admin-dodaj-fakultet.component';

describe('AdminDodajFakultetComponent', () => {
  let component: AdminDodajFakultetComponent;
  let fixture: ComponentFixture<AdminDodajFakultetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminDodajFakultetComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminDodajFakultetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
