import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikaziProgramComponent } from './prikazi-program.component';

describe('PrikaziProgramComponent', () => {
  let component: PrikaziProgramComponent;
  let fixture: ComponentFixture<PrikaziProgramComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrikaziProgramComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrikaziProgramComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
