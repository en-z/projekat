import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentiPredmetaComponent } from './studenti-predmeta.component';

describe('StudentiPredmetaComponent', () => {
  let component: StudentiPredmetaComponent;
  let fixture: ComponentFixture<StudentiPredmetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudentiPredmetaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudentiPredmetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
