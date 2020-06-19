<?php

defined('BASEPATH') or exit('No direct script access allowed');

// This can be removed if you use __autoload() in config.php OR use Modular Extensions
/** @noinspection PhpIncludeInspection */
require APPPATH . '/libraries/REST_Controller.php';

// use namespace
use Restserver\Libraries\REST_Controller;

/**
 * This is an example of a few basic user interaction methods you could use
 * all done with a hardcoded array
 *
 * @package         CodeIgniter
 * @subpackage      Rest Server
 * @category        Controller
 * @author          Phil Sturgeon, Chris Kacerguis
 * @license         MIT
 * @link            https://github.com/chriskacerguis/codeigniter-restserver
 */
class Logbook extends REST_Controller
{
    function __construct()
    {
        parent::__construct();
        $this->load->model('M_log', 'api');
    }

    public function log_get()
    {
        $method = $_SERVER['REQUEST_METHOD'];

        $response  = [];

        if ($method == "GET") {
            $id_log = $this->input->get('id_log');
            if ($id_log == null) {
                $fetchAll = $this->api->getAllLog();

                foreach ($fetchAll as $fetch) {
                    $get = [
                        'id_log' => $fetch['id_log'],
                        'judul' => $fetch['judul'],
                        'uraian' => $fetch['uraian'],
                        'id_user' => $fetch['id_user']
                    ];

                    array_push($response, $get);
                }
            } else {
                $fetchIdLog = $this->api->getIdLog($id_log);

                if ($fetchIdLog->num_rows() > 0) {

                    $fetch = $fetchIdLog->row_array();
                    $get = [
                        'id_log' => $fetch['id_log'],
                        'judul' => $fetch['judul'],
                        'uraian' => $fetch['uraian'],
                        'id_user' => $fetch['id_user']
                    ];
                } else {
                    $get = [
                        'id log' => null,
                        'judul' => "Data tidak ditemukan",
                        'uraian' => "Data tidak ditemukan"
                    ];
                }

                array_push($response, $get);
            }
            echo header("Content-Type: application/json");
            echo json_encode($response);
        }
    }

    public function log_post()
    {
        $method = $_SERVER['REQUEST_METHOD'];
        $response  = [];

        if ($method == 'POST') {

            $judul = $this->input->post('judul');
            $uraian = $this->input->post('uraian');
            $id_user = $this->input->post('id_user');
            $data_log = [
                'judul'    => $judul,
                'uraian' => $uraian,
                'id_user' => $id_user
            ];
            $this->api->insertLogbook($judul, $uraian, $id_user);

            $post = [
                'status' => "Sukses",
                'message' => "Berhasil"
            ];

            array_push($response, $post);
            echo header("Content-Type: application/json");
            echo json_encode($response);
        }
    }

    public function log_put()
    {
        $method = $_SERVER['REQUEST_METHOD'];
        $response  = [];
        if ($method == 'PUT') {
            parse_str(file_get_contents("php://input"), $data);

            if ($this->api->getIdLog($data['id_log'])->num_rows() > 0) {
                $this->api->updateLogbook($data['id_log'], $data['judul'], $data['uraian'], $data['id_user']);

                $put = [
                    'status' => "Sukses",
                    'message' => "Berhasil merubah data"
                ];
            } else {
                $put = [
                    'status' => "Gagal",
                    'message' => "Gagal merubah data"
                ];
            }

            array_push($response, $put);
            echo header("Content-Type: application/json");
            echo json_encode($response);
        }
    }
    public function log_delete()
    {
        $method = $_SERVER['REQUEST_METHOD'];
        $response  = [];
        if ($method == 'DELETE') {
            parse_str(file_get_contents("php://input"), $data);

            if ($this->api->getIdLog($data['id_log'])->num_rows() > 0) {
                $this->api->deleteLogbook($data['id_log']);

                $delete = [
                    'status' => "Sukses",
                    'message' => "Berhasil menghapus data"
                ];
            } else {
                $delete = [
                    'status' => "Gagal",
                    'message' => "Gagal menghapus data"
                ];
            }

            array_push($response, $delete);
            echo header("Content-Type: application/json");
            echo json_encode($response);
        }
    }
}
