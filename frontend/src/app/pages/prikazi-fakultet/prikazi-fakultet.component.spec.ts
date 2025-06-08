import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikaziFakultetComponent } from './prikazi-fakultet.component';

describe('PrikaziFakultetComponent', () => {
  let component: PrikaziFakultetComponent;
  let fixture: ComponentFixture<PrikaziFakultetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrikaziFakultetComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrikaziFakultetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
