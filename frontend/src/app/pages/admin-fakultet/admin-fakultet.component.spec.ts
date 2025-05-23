import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminFakultetComponent } from './admin-fakultet.component';

describe('AdminFakultetComponent', () => {
  let component: AdminFakultetComponent;
  let fixture: ComponentFixture<AdminFakultetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminFakultetComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminFakultetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
