import { Component } from '@angular/core';
import { DokumentDTO } from '../../../models/dokumentDto';
import { DokumentDtoService } from '../../../services/dokumentiDto.service';
import { UserService } from '../../../services/user.service';
import { User } from '../../../models/user';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-prikazi-dokument',
  imports: [CommonModule,FormsModule],
  templateUrl: './prikazi-dokument.component.html',
  styleUrl: './prikazi-dokument.component.css'
})
export class PrikaziDokumentComponent {
  dokumenti: DokumentDTO[] = [];
  page = 0;
  size = 5;
  totalPages = 0;
  user: User[]=[];
  selectedUserId:number|null = null;
  searchEmail:string="";

  constructor(private dokumentService: DokumentDtoService,private userService:UserService) {}

  ngOnInit(): void {
    this.fetchDokumenti();
  }

  nextPage() {
    if (this.page + 1 < this.totalPages) {
      this.page++;
      this.fetchDokumenti();
    }
  }

  prevPage() {
    if (this.page > 0) {
      this.page--;
      this.fetchDokumenti();
    }
  }
  searchUsersByEmail() {
    this.userService.getByEmail(this.searchEmail).subscribe(users => {
      this.user= users;
    });
  }

  selectUser(userId: number) {
    this.selectedUserId = userId;
    this.page = 0;
    this.fetchDokumenti();
  }

  clearUserFilter() {
    this.selectedUserId = null;
    this.fetchDokumenti();
  }

  fetchDokumenti() {
    const request$ = this.selectedUserId != null
      ? this.dokumentService.getDokumentiByUserId(this.selectedUserId, this.page, this.size)
      : this.dokumentService.getDokumenti(this.page, this.size);

    request$.subscribe(data => {
      this.dokumenti = data.content;
      console.log(this.dokumenti)
      this.totalPages = data.totalPages;

    });
  }

}
