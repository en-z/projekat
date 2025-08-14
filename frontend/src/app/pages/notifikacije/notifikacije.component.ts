import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { Notifikacije } from '../../models/Notifikacije';

@Component({
  selector: 'app-notifikacije',
  standalone:true,
  imports: [CommonModule],
  templateUrl: './notifikacije.component.html',
  styleUrl: './notifikacije.component.css'
})
export class NotifikacijeComponent {

  poruke: Notifikacije[] = [];
  prikazi = false;
  private hideTimeout:any;

  constructor(private http: HttpClient, private authService: AuthService) {}

  ngOnInit(): void {
    this.ucitajPoruke();
  }

  togglePrikaz(): void {
    this.prikazi = !this.prikazi;
  }

  ucitajPoruke(): void {
    const endpoint = this.authService.hasRole('ROLE_ADMIN')
      ? 'http://localhost:8080/api/biblioteka/notifikacije/admin'
      : 'http://localhost:8080/api/biblioteka/notifikacije';

    this.http.get<Notifikacije[]>(endpoint).subscribe({
      next: data => this.poruke = data,
      error: err => console.error('Greška pri dohvatanju notifikacija:', err)
    });
  }

  onMenuEnter():void{
    this.clearHideTimeout();
  }
  onMenuLeave():void{
    this.hideTimeout=setTimeout(()=>{
      this.prikazi=false;},50);
    
  }
  private clearHideTimeout():void{
    if(this.hideTimeout){
      clearTimeout(this.hideTimeout);
      this.hideTimeout=null;
    }
  }
}
