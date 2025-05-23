import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDodjaUniverziteComponent } from './admin-dodja-univerzite.component';

describe('AdminDodjaUniverziteComponent', () => {
  let component: AdminDodjaUniverziteComponent;
  let fixture: ComponentFixture<AdminDodjaUniverziteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminDodjaUniverziteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminDodjaUniverziteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
