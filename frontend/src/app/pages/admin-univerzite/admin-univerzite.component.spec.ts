import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminUniverziteComponent } from './admin-univerzite.component';

describe('AdminUniverziteComponent', () => {
  let component: AdminUniverziteComponent;
  let fixture: ComponentFixture<AdminUniverziteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminUniverziteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminUniverziteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
