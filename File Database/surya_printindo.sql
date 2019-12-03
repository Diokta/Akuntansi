-- phpMyAdmin SQL Dump
-- version 2.10.2
-- http://www.phpmyadmin.net
-- 
-- Host: localhost
-- Waktu pembuatan: 09. Oktober 2015 jam 20:48
-- Versi Server: 5.0.45
-- Versi PHP: 5.2.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- Database: `surya_printindo`
-- 

-- --------------------------------------------------------

-- 
-- Struktur dari tabel `admin`
-- 

CREATE TABLE `admin` (
  `id` varchar(10) NOT NULL,
  `nama_lengkap` varchar(30) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `level` varchar(30) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 
-- Dumping data untuk tabel `admin`
-- 

INSERT INTO `admin` VALUES ('AD001', 'Indra Lesmana', 'indra', 'indra', 'Admin Keuangan');

-- --------------------------------------------------------

-- 
-- Struktur dari tabel `beban`
-- 

CREATE TABLE `beban` (
  `id` varchar(4) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `keterangan` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 
-- Dumping data untuk tabel `beban`
-- 

INSERT INTO `beban` VALUES ('5001', 'Beban Air', 'Rekening Air');
INSERT INTO `beban` VALUES ('5002', 'Beban Perlengkapan', 'Perlengkapan Kantor');
INSERT INTO `beban` VALUES ('5003', 'Beban Listrik', 'Rekening Listrik');
INSERT INTO `beban` VALUES ('5004', 'Beban Sewa', 'Sewa Gedung');
INSERT INTO `beban` VALUES ('5005', 'Beban Telepon', 'Rekening Telepon');
INSERT INTO `beban` VALUES ('5006', 'Beban Iklan', 'Promosi');

-- --------------------------------------------------------

-- 
-- Struktur dari tabel `buku_besar`
-- 

CREATE TABLE `buku_besar` (
  `tanggal` date NOT NULL,
  `keterangan` text NOT NULL,
  `ref` varchar(10) NOT NULL,
  `nama_akun` varchar(100) NOT NULL,
  `sort` int(11) NOT NULL,
  `normal` varchar(20) default NULL,
  `debit` bigint(20) NOT NULL,
  `kredit` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 
-- Dumping data untuk tabel `buku_besar`
-- 

INSERT INTO `buku_besar` VALUES ('2015-05-19', 'Beban Perlengkapan', 'PB001', 'Kas', 1, NULL, 0, 130000);
INSERT INTO `buku_besar` VALUES ('2015-05-19', 'Beban Perlengkapan', 'PB001', 'Beban Perlengkapan', 2, NULL, 130000, 0);
INSERT INTO `buku_besar` VALUES ('2015-05-19', 'Penyewaan Mesin FC Xerox FX 001 3 bulan', 'PE001', 'Kas', 1, NULL, 660000, 0);
INSERT INTO `buku_besar` VALUES ('2015-05-19', 'Penyewaan Mesin FC Xerox FX 001 3 bulan', 'PE001', 'Penyewaan Mesin FC Xerox FX 001', 2, NULL, 0, 660000);
INSERT INTO `buku_besar` VALUES ('2015-05-19', 'Beban Iklan', 'PB002', 'Kas', 1, NULL, 0, 620000);
INSERT INTO `buku_besar` VALUES ('2015-05-19', 'Beban Iklan', 'PB002', 'Beban Iklan', 2, NULL, 620000, 0);

-- --------------------------------------------------------

-- 
-- Struktur dari tabel `jurnal`
-- 

CREATE TABLE `jurnal` (
  `tanggal` date NOT NULL,
  `ref` varchar(10) NOT NULL,
  `keterangan` text NOT NULL,
  `nama_akun` varchar(100) default NULL,
  `kelompok` varchar(20) default NULL,
  `normal` varchar(20) default NULL,
  `debit` bigint(20) NOT NULL,
  `kredit` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 
-- Dumping data untuk tabel `jurnal`
-- 

INSERT INTO `jurnal` VALUES ('2015-05-19', 'PB001', 'Beban Perlengkapan', NULL, NULL, NULL, 130000, 0);
INSERT INTO `jurnal` VALUES ('2015-05-19', 'PB001', '          Kas', NULL, NULL, NULL, 0, 130000);
INSERT INTO `jurnal` VALUES ('2015-05-19', 'PE001', 'Kas', NULL, NULL, NULL, 660000, 0);
INSERT INTO `jurnal` VALUES ('2015-05-19', 'PE001', '          Penyewaan Mesin FC Xerox FX 001 3 bulan', NULL, NULL, NULL, 0, 660000);
INSERT INTO `jurnal` VALUES ('2015-05-19', 'PB002', 'Beban Iklan', NULL, NULL, NULL, 620000, 0);
INSERT INTO `jurnal` VALUES ('2015-05-19', 'PB002', '          Kas', NULL, NULL, NULL, 0, 620000);

-- --------------------------------------------------------

-- 
-- Struktur dari tabel `laba_rugi`
-- 

CREATE TABLE `laba_rugi` (
  `ref` varchar(10) NOT NULL,
  `tanggal` date NOT NULL,
  `kelompok` varchar(50) NOT NULL,
  `nama_akun` varchar(100) NOT NULL,
  `nominal` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 
-- Dumping data untuk tabel `laba_rugi`
-- 

INSERT INTO `laba_rugi` VALUES ('PB001', '2015-05-19', 'Beban Operasional', 'Beban Perlengkapan', 130000);
INSERT INTO `laba_rugi` VALUES ('PE001', '2015-05-19', 'Pendapatan', 'Penyewaan Mesin FC Xerox FX 001', 660000);
INSERT INTO `laba_rugi` VALUES ('PB002', '2015-05-19', 'Beban Operasional', 'Beban Iklan', 620000);

-- --------------------------------------------------------

-- 
-- Struktur dari tabel `pelanggan`
-- 

CREATE TABLE `pelanggan` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `jenis_kelamin` varchar(20) NOT NULL,
  `alamat` text NOT NULL,
  `no_ktp` varchar(30) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 
-- Dumping data untuk tabel `pelanggan`
-- 

INSERT INTO `pelanggan` VALUES ('PL001', 'Supriman', 'Laki-laki', 'Jl. xxxxxxxxxxxxx', '22300223232');
INSERT INTO `pelanggan` VALUES ('PL002', 'Sukijan', 'Laki-laki', 'Jl. xxxxxxxxxxxxxxx', '2211102020');

-- --------------------------------------------------------

-- 
-- Struktur dari tabel `pembayaran_beban`
-- 

CREATE TABLE `pembayaran_beban` (
  `id` varchar(10) NOT NULL,
  `tanggal` date NOT NULL,
  `id_beban` varchar(10) NOT NULL,
  `nominal` bigint(20) NOT NULL,
  `keterangan` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 
-- Dumping data untuk tabel `pembayaran_beban`
-- 

INSERT INTO `pembayaran_beban` VALUES ('PB001', '2015-05-19', '5002', 130000, '-');
INSERT INTO `pembayaran_beban` VALUES ('PB002', '2015-05-19', '5006', 620000, '-');

-- --------------------------------------------------------

-- 
-- Struktur dari tabel `pengembalian`
-- 

CREATE TABLE `pengembalian` (
  `id` varchar(10) NOT NULL,
  `id_penyewaan` varchar(10) NOT NULL,
  `tanggal_dikembalikan` date NOT NULL,
  `lama_telat` int(11) NOT NULL,
  `total_denda` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data untuk tabel `pengembalian`
-- 


-- --------------------------------------------------------

-- 
-- Struktur dari tabel `penyewaan`
-- 

CREATE TABLE `penyewaan` (
  `no_faktur` varchar(10) NOT NULL,
  `tanggal` date NOT NULL,
  `id_pelanggan` varchar(10) NOT NULL,
  `id_produk_sewa` varchar(10) NOT NULL,
  `lama_sewa` int(11) NOT NULL,
  `total` bigint(20) NOT NULL,
  PRIMARY KEY  (`no_faktur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 
-- Dumping data untuk tabel `penyewaan`
-- 

INSERT INTO `penyewaan` VALUES ('PE001', '2015-05-19', 'PL001', 'PS002', 3, 660000);

-- --------------------------------------------------------

-- 
-- Struktur dari tabel `produk_sewa`
-- 

CREATE TABLE `produk_sewa` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `spesifikasi` text NOT NULL,
  `harga_sewa` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 
-- Dumping data untuk tabel `produk_sewa`
-- 

INSERT INTO `produk_sewa` VALUES ('PS001', 'Mesin FC Canon ICMF 3000', '- Copy, Print, Scan Color dan Simplex\n- Speed 18 Lembar per menit\n- Maksimal Ukuran Kertas A4', 250000);
INSERT INTO `produk_sewa` VALUES ('PS002', 'Mesin FC Xerox FX 001', '- Print, Scan\n- A4, Letter (HVS)\n- Ukuran 409 x 362 x 232mm\n- Berat 9.5 Kg\n- 400 Watt', 220000);
