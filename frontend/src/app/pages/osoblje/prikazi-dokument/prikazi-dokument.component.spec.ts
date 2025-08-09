import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikaziDokumentComponent } from './prikazi-dokument.component';

describe('PrikaziDokumentComponent', () => {
  let component: PrikaziDokumentComponent;
  let fixture: ComponentFixture<PrikaziDokumentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrikaziDokumentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrikaziDokumentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
