import { Component } from '@angular/core';
import { DokumentRequest } from '../../../models/dokument';
import { DokumentService } from '../../../services/dokument.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../../services/user.service';
import { User } from '../../../models/user';

@Component({
  selector: 'app-dokumenti',
  imports: [CommonModule,FormsModule],
  templateUrl: './dokumenti.component.html',
  styleUrl: './dokumenti.component.css'
})
export class DokumentiComponent {
  model: Partial<DokumentRequest> = {};
  selectedFile!: File;

  constructor(private dokumentService: DokumentService,private userService:UserService) {}

  onFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
    }
  }

  onSubmit(): void {
    if (!this.selectedFile) {
      alert('Morate izabrati fajl.');
      return;
    }

    const request: DokumentRequest = {
      naslov: this.model.naslov!,
      opis: this.model.opis || '',
      userId: this.model.userId!,
      file: this.selectedFile
    };

    this.dokumentService.uploadDokument(request).subscribe({
    });
  }
  emailSearch = '';
  foundUsers: User[] = [];
  selectedUser: any = null;

  searchUserByEmail() {
    if (this.emailSearch.length < 3) return;

    this.userService.getByEmail(this.emailSearch).subscribe(res => {
      this.foundUsers = res;
    });
  }

  selectUser(user: any) {
    this.selectedUser = user;
    this.model.userId = user.id;
    this.foundUsers = [];
  }
}
