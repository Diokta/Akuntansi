-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 04, 2019 at 06:10 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `program_akuntansi`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` varchar(10) NOT NULL,
  `nama_lengkap` varchar(30) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `level` varchar(30) NOT NULL,
  `email` varchar(35) DEFAULT NULL,
  `telepon` varchar(20) DEFAULT NULL,
  `alamat` text DEFAULT NULL,
  `tahun_pembukuan` int(11) DEFAULT NULL,
  `bulan_akhir_pembukuan` tinyint(4) DEFAULT NULL,
  `bulan_awal_pembukuan` tinyint(4) DEFAULT NULL,
  `periode_pembukuan` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `nama_lengkap`, `username`, `password`, `level`, `email`, `telepon`, `alamat`, `tahun_pembukuan`, `bulan_akhir_pembukuan`, `bulan_awal_pembukuan`, `periode_pembukuan`) VALUES
('AD001', 'Difan', 'difan', 'difan', 'Admin Keuangan', 'difan@email.com', '12345678', 'Jalan Salemba Raya', 2019, 1, 1, 12),
('AD002', 'test', 'test', '12345', 'Admin Keuangan', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `akun`
--

CREATE TABLE `akun` (
  `id` varchar(4) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `akun`
--

INSERT INTO `akun` (`id`, `nama`, `keterangan`) VALUES
('1001', 'Kas', 'Kas Utama'),
('5001', 'Beban Air', 'Rekening Air'),
('5002', 'Beban Perlengkapan', 'Perlengkapan Kantor'),
('5003', 'Beban Listrik', 'Rekening Listrik'),
('5004', 'Beban Sewa', 'Sewa Gedung'),
('5006', 'Beban Iklan', 'Promosi');

-- --------------------------------------------------------

--
-- Table structure for table `buku_besar`
--

CREATE TABLE `buku_besar` (
  `tanggal` date NOT NULL,
  `keterangan` text NOT NULL,
  `ref` varchar(10) NOT NULL,
  `nama_akun` varchar(100) NOT NULL,
  `sort` int(11) NOT NULL,
  `normal` varchar(20) DEFAULT NULL,
  `debit` bigint(20) NOT NULL,
  `kredit` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `buku_besar`
--

INSERT INTO `buku_besar` (`tanggal`, `keterangan`, `ref`, `nama_akun`, `sort`, `normal`, `debit`, `kredit`) VALUES
('2015-05-19', 'Beban Perlengkapan', 'PB001', 'Kas', 1, NULL, 0, 130000),
('2015-05-19', 'Beban Perlengkapan', 'PB001', 'Beban Perlengkapan', 2, NULL, 130000, 0),
('2015-05-19', 'Penyewaan Mesin FC Xerox FX 001 3 bulan', 'PE001', 'Kas', 1, NULL, 660000, 0),
('2015-05-19', 'Penyewaan Mesin FC Xerox FX 001 3 bulan', 'PE001', 'Penyewaan Mesin FC Xerox FX 001', 2, NULL, 0, 660000),
('2015-05-19', 'Beban Iklan', 'PB002', 'Kas', 1, NULL, 0, 620000),
('2015-05-19', 'Beban Iklan', 'PB002', 'Beban Iklan', 2, NULL, 620000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `jurnal`
--

CREATE TABLE `jurnal` (
  `id` varchar(4) NOT NULL,
  `tanggal` date NOT NULL,
  `keterangan` text NOT NULL,
  `debit` bigint(20) NOT NULL,
  `kredit` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `laba_rugi`
--

CREATE TABLE `laba_rugi` (
  `ref` varchar(10) NOT NULL,
  `tanggal` date NOT NULL,
  `kelompok` varchar(50) NOT NULL,
  `nama_akun` varchar(100) NOT NULL,
  `nominal` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `laba_rugi`
--

INSERT INTO `laba_rugi` (`ref`, `tanggal`, `kelompok`, `nama_akun`, `nominal`) VALUES
('PB001', '2015-05-19', 'Beban Operasional', 'Beban Perlengkapan', 130000),
('PE001', '2015-05-19', 'Pendapatan', 'Penyewaan Mesin FC Xerox FX 001', 660000),
('PB002', '2015-05-19', 'Beban Operasional', 'Beban Iklan', 620000);

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `jenis_kelamin` varchar(20) NOT NULL,
  `alamat` text NOT NULL,
  `no_ktp` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id`, `nama`, `jenis_kelamin`, `alamat`, `no_ktp`) VALUES
('PL001', 'Supriman', 'Laki-laki', 'Jl. xxxxxxxxxxxxx', '22300223232'),
('PL002', 'Sukijan', 'Laki-laki', 'Jl. xxxxxxxxxxxxxxx', '2211102020');

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran_beban`
--

CREATE TABLE `pembayaran_beban` (
  `id` varchar(10) NOT NULL,
  `tanggal` date NOT NULL,
  `id_beban` varchar(10) NOT NULL,
  `nominal` bigint(20) NOT NULL,
  `keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pembayaran_beban`
--

INSERT INTO `pembayaran_beban` (`id`, `tanggal`, `id_beban`, `nominal`, `keterangan`) VALUES
('PB001', '2015-05-19', '5002', 130000, '-'),
('PB002', '2015-05-19', '5006', 620000, '-');

-- --------------------------------------------------------

--
-- Table structure for table `pengembalian`
--

CREATE TABLE `pengembalian` (
  `id` varchar(10) NOT NULL,
  `id_penyewaan` varchar(10) NOT NULL,
  `tanggal_dikembalikan` date NOT NULL,
  `lama_telat` int(11) NOT NULL,
  `total_denda` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `penyewaan`
--

CREATE TABLE `penyewaan` (
  `no_faktur` varchar(10) NOT NULL,
  `tanggal` date NOT NULL,
  `id_pelanggan` varchar(10) NOT NULL,
  `id_produk_sewa` varchar(10) NOT NULL,
  `lama_sewa` int(11) NOT NULL,
  `total` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `penyewaan`
--

INSERT INTO `penyewaan` (`no_faktur`, `tanggal`, `id_pelanggan`, `id_produk_sewa`, `lama_sewa`, `total`) VALUES
('PE001', '2015-05-19', 'PL001', 'PS002', 3, 660000);

-- --------------------------------------------------------

--
-- Table structure for table `perusahaan`
--

CREATE TABLE `perusahaan` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(35) NOT NULL,
  `email` varchar(35) NOT NULL,
  `password` varchar(20) NOT NULL,
  `telepon` varchar(20) NOT NULL,
  `alamat` text NOT NULL,
  `tahun_pembukuan` int(11) NOT NULL,
  `bulan_akhir_pembukuan` tinyint(4) NOT NULL,
  `bulan_awal_pembukuan` tinyint(4) NOT NULL,
  `periode_pembukuan` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `produk_sewa`
--

CREATE TABLE `produk_sewa` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `spesifikasi` text NOT NULL,
  `harga_sewa` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `produk_sewa`
--

INSERT INTO `produk_sewa` (`id`, `nama`, `spesifikasi`, `harga_sewa`) VALUES
('PS001', 'Mesin FC Canon ICMF 3000', '- Copy, Print, Scan Color dan Simplex\n- Speed 18 Lembar per menit\n- Maksimal Ukuran Kertas A4', 250000),
('PS002', 'Mesin FC Xerox FX 001', '- Print, Scan\n- A4, Letter (HVS)\n- Ukuran 409 x 362 x 232mm\n- Berat 9.5 Kg\n- 400 Watt', 220000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id` int(11) NOT NULL,
  `id_akun` varchar(4) CHARACTER SET utf8 NOT NULL,
  `id_jurnal` varchar(4) CHARACTER SET utf8 NOT NULL,
  `debit` int(11) NOT NULL,
  `kredit` int(11) NOT NULL,
  `keterangan` text CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `akun`
--
ALTER TABLE `akun`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `jurnal`
--
ALTER TABLE `jurnal`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pembayaran_beban`
--
ALTER TABLE `pembayaran_beban`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `penyewaan`
--
ALTER TABLE `penyewaan`
  ADD PRIMARY KEY (`no_faktur`);

--
-- Indexes for table `produk_sewa`
--
ALTER TABLE `produk_sewa`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `akun_constraint` (`id_akun`),
  ADD KEY `jurnal_constraint` (`id_jurnal`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `akun_constraint` FOREIGN KEY (`id_akun`) REFERENCES `akun` (`id`),
  ADD CONSTRAINT `jurnal_constraint` FOREIGN KEY (`id_jurnal`) REFERENCES `jurnal` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
