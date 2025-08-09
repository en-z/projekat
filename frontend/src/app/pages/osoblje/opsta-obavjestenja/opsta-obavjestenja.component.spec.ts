import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OpstaObavjestenjaComponent } from './opsta-obavjestenja.component';

describe('OpstaObavjestenjaComponent', () => {
  let component: OpstaObavjestenjaComponent;
  let fixture: ComponentFixture<OpstaObavjestenjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OpstaObavjestenjaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OpstaObavjestenjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
