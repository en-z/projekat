import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { ZavrsniRadService } from '../../services/zavrsni.rad.service';

@Component({
  selector: 'app-prikazi-zavrsne-radove',
  imports: [CommonModule],
  templateUrl: './prikazi-zavrsne-radove.component.html',
  styleUrls: ['./prikazi-zavrsne-radove.component.css']
})
export class PrikaziZavrsneRadoveComponent implements OnInit {
  zavrsniRadovi: any[] = [];

  constructor(private zavrsniRadService: ZavrsniRadService, private authService: AuthService) {
  }

  ngOnInit(): void {
    const nastavnikId = this.authService.currentUserId;
    if (nastavnikId) {
      this.ucitajZavrsneRadove(nastavnikId);
    }
  }

  ucitajZavrsneRadove(nastavnikId: number) {
    this.zavrsniRadService.getByMentor(nastavnikId).subscribe(data => {
      this.zavrsniRadovi = data;
    });
  }

  preuzmiFajl(zavrsniRadId: number, fileName: string) {
    this.zavrsniRadService.downloadFile(zavrsniRadId).subscribe({
      next: (response) => {
        const blob = new Blob([response.body!], {
          type: response.headers.get('Content-Type') || 'application/octet-stream'
        });
        const a = document.createElement('a');
        a.href = window.URL.createObjectURL(blob);
        a.download = fileName;
        a.click();
        window.URL.revokeObjectURL(a.href);
      },
      error: (err) => {
        console.error('Greška pri preuzimanju fajla:', err);
        alert('Došlo je do greške pri preuzimanju fajla.')
      }
    });
  }
}
